type Severity = "success" | "secondary" | "info" | "warning" | "danger" | "contrast" | undefined;

export function inventoryFunction(status: string): Severity {
  switch (status) {
    case 'INSTOCK':
      return 'success';
    case 'LOWSTOCK':
      return 'warning';
    case 'OUTOFSTOCK':
      return 'danger';
    default:
      return 'info';
  }
}

export function getInventoryName(status: string): string {
  switch (status) {
    case 'INSTOCK':
      return 'EN STOCK';
    case 'LOWSTOCK':
      return 'FAIBLE STOCK';
    case 'OUTOFSTOCK':
      return 'EN RUPTURE DE STOCK';
    default:
      return 'ÉTAT INCONNU';
  }
}
