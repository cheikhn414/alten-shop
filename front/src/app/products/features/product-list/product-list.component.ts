import { Component, OnInit, inject, signal } from "@angular/core";
import { Product } from "app/products/data-access/product.model";
import { ProductsService } from "app/products/data-access/products.service";
import { ProductFormComponent } from "app/products/ui/product-form/product-form.component";
import { ButtonModule } from "primeng/button";
import { CardModule } from "primeng/card";
import { DataViewModule } from 'primeng/dataview';
import { DialogModule } from 'primeng/dialog';
import {RatingModule} from "primeng/rating";
import {PaginatorModule} from "primeng/paginator";
import {CurrencyPipe} from "@angular/common";
import {TagModule} from "primeng/tag";
import {getInventoryName, inventoryFunction} from "../../../shared/utils/inventory.function";
import {CartItem} from "../../data-access/cart.model";
import {CartService} from "../../data-access/cart.service";
import {MessageService} from "primeng/api";
import {ToastModule} from "primeng/toast";

const emptyProduct: Product = {
  id: 0,
  code: "",
  name: "",
  description: "",
  image: "",
  category: "",
  price: 0,
  quantity: 0,
  internalReference: "",
  shellId: 0,
  inventoryStatus: "INSTOCK",
  rating: 0,
  createdAt: 0,
  updatedAt: 0,
};

const emptyCartItem: CartItem = {
  id: 0,
  product: emptyProduct,
  quantity: 0,
  createdAt: 0,
  updatedAt: 0,
};

@Component({
  selector: "app-product-list",
  templateUrl: "./product-list.component.html",
  styleUrls: ["./product-list.component.scss"],
  standalone: true,
  imports: [DataViewModule, CardModule, ButtonModule, DialogModule, ProductFormComponent, RatingModule, PaginatorModule, CurrencyPipe, TagModule, ToastModule],
})
export class ProductListComponent implements OnInit {
  private readonly productsService = inject(ProductsService);
  private readonly cartService = inject(CartService);
  private readonly messageService = inject(MessageService);

  protected readonly getInventorySeverity = inventoryFunction;
  protected readonly getInventoryName = getInventoryName;

  public readonly products = this.productsService.products;

  public isDialogVisible = false;
  public isCreation = false;
  public readonly editedProduct = signal<Product>(emptyProduct);

  ngOnInit() {
    this.productsService.get().subscribe();
  }

  public onCreate() {
    this.isCreation = true;
    this.isDialogVisible = true;
    this.editedProduct.set(emptyProduct);
  }

  public onUpdate(product: Product) {
    this.isCreation = false;
    this.isDialogVisible = true;
    this.editedProduct.set(product);
  }

  public onDelete(product: Product) {
    this.productsService.delete(product.id).subscribe();
  }

  public onSave(product: Product) {
    if (this.isCreation) {
      this.productsService.create(product).subscribe();
    } else {
      this.productsService.update(product).subscribe();
    }
    this.closeDialog();
  }

  public onCancel() {
    this.closeDialog();
  }

  private closeDialog() {
    this.isDialogVisible = false;
  }

  showToast(summary: string, detail: string, severity: string) {
    this.messageService.add({severity, summary, detail, life: 3000});
  }

  isProductInCart(productId: number) {
    return this.cartService.isInCart(productId);
  }

  addProductToCart(product: Product) {
    const cartItem: CartItem = {
      product: product,
      quantity: 1
    };
    this.cartService.addToCart(cartItem);
    this.showToast('Succès', `${product.name} ajouté au panier`, 'success');
  }

  removeProductToCart(product: Product) {
    this.cartService.removeFromCart(product.id);
    this.showToast('Avertissement', `${product.name} supprimé du panier`, 'warn');
  }
}
