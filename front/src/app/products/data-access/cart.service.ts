import {computed, Injectable, signal} from "@angular/core";
import {CartItem} from "./cart.model";

@Injectable({
  providedIn: "root"
})
export class CartService {

  private readonly _cartItems = signal<CartItem[]>([]);

  public readonly items = this._cartItems.asReadonly();

  public readonly totalCount = computed(() => this._cartItems().length);

  addToCart(cartItem: CartItem) {
    this._cartItems.update((items) => [...items, cartItem]);
  }

  removeFromCart(productId: number) {
    this._cartItems.update((items) => items.filter(item => item.product.id !== productId));
  }

  isInCart(productId: number): boolean {
    return this._cartItems().some(item => item.product.id === productId);
  }

  clearCart() {
    this._cartItems.set([]);
  }
}
