import {Component, inject, Signal, ViewEncapsulation} from "@angular/core";
import {TableModule} from "primeng/table";
import {CurrencyPipe, NgIf} from "@angular/common";
import {RatingModule} from "primeng/rating";
import {TagModule} from "primeng/tag";
import {CartService} from "../../data-access/cart.service";
import {FormsModule} from "@angular/forms";
import {DataViewModule} from "primeng/dataview";
import {Button} from "primeng/button";
import {ChipModule} from "primeng/chip";
import {TooltipModule} from "primeng/tooltip";
import {ToastModule} from "primeng/toast";
import {MessageModule} from "primeng/message";
import {getInventoryName, inventoryFunction} from "app/shared/utils/inventory.function";
import {InputTextModule} from "primeng/inputtext";
import {CartItem} from "../../data-access/cart.model";

@Component({
  selector: "app-cart",
  template: `
    <div class="card">
      <p-table [value]="items()" [tableStyle]="{ 'min-width': '60rem' }">
        <ng-template pTemplate="caption">
          <div class="flex items-center justify-content-between ">
            <span class="text-xl font-bold pt-3">{{ items().length }} article<span *ngIf="items().length > 1">s</span> dans le panier</span>
            <p-button icon="pi pi-trash" [outlined]="true" severity="danger" (click)="removeAllItems()"
                      label="Vider le panier" size="small"/>
          </div>
        </ng-template>
        <ng-template pTemplate="header">
          <tr>
            <th>Nom</th>
            <th>Image</th>
            <th>Prix</th>
            <th>Quantité</th>
            <th>Catégorie</th>
            <th>Note</th>
            <th>Statut</th>
            <th></th>
          </tr>
        </ng-template>
        <ng-template pTemplate="body" let-item>
          <tr>
            <td>{{ item.product.name }}</td>
            <td>
              <img
                [src]="'https://primefaces.org/cdn/primeng/images/demo/product/' + item.product.image"
                [alt]="item.product.name"
                class="w-24 rounded"
              />
            </td>
            <td>{{ item.product.price | currency: 'USD' }}</td>
            <td><input pInputText type="number" [(ngModel)]="item.product.quantity" min="1" max="100"/></td>
            <td>{{ item.product.category }}</td>
            <td>
              <p-rating [(ngModel)]="item.product.rating" [readonly]="true" [cancel]="false"/>
              <span class="pl-1">({{ item.product.rating }}/5)</span>
            </td>
            <td>
              <p-tag [value]="getInventoryName(item.product.inventoryStatus)"
                     [severity]="getInventorySeverity(item.product.inventoryStatus)"/>
            </td>
            <td>
              <p-button icon="pi pi-times" label="Supprimer" text="true" severity="danger" size="small"
                        (click)="removeItem(item.product.id)"/>
            </td>
          </tr>
        </ng-template>
        <ng-template pTemplate="emptymessage">
          <tr>
            <td colspan="6" class="margin-auto">Il n'y a pas encore d'article dans ce panier.</td>
          </tr>
        </ng-template>
      </p-table>
    </div>
  `,
  standalone: true,
  imports: [
    TableModule,
    RatingModule,
    TagModule,
    FormsModule,
    DataViewModule,
    CurrencyPipe,
    Button,
    ChipModule,
    TooltipModule,
    ToastModule,
    MessageModule,
    NgIf,
    InputTextModule
  ],
  encapsulation: ViewEncapsulation.None
})
export class CartComponent {
  readonly cartService = inject(CartService);

  public readonly items: Signal<CartItem[]> = this.cartService.items;
  protected readonly getInventorySeverity = inventoryFunction;
  protected readonly getInventoryName = getInventoryName;

  removeAllItems() {
    this.cartService.clearCart();
  }

  removeItem(productId: number) {
    this.cartService.removeFromCart(productId);
  }
}
