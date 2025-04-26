import {computed, Injectable, signal} from "@angular/core";
import {CartItem} from "./cart.model";

@Injectable({
  providedIn: "root"
}) export class CartService {

  private readonly _cartItems  = signal<CartItem[]>([]);

  public readonly items = this._cartItems.asReadonly();

  public readonly totalCount = computed(() => this._cartItems().length);

  readonly totalPrice = computed(() =>
    this._cartItems().reduce((sum, item) => sum + item.product.price, 0)
  );

  addToCart(cartItem: CartItem) {
    this._cartItems.update((items) => [...items, cartItem]);
  }

  removeFromCart(cartItemId: number) {
    this._cartItems.update((items) => items.filter(item => item.id !== cartItemId));
  }

  isInCart(cartItemId: number): boolean {
    return this._cartItems().some(item => item.id === cartItemId);
  }

  clearCart() {
    this._cartItems.set([]);
  }
}
