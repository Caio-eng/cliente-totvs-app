export function formatDate(isoDate: string, type: 'br' | 'iso' = 'br'): string {
  if (isoDate) {
    const date = new Date(Date.UTC(
      parseInt(isoDate.slice(0, 4), 10), // Ano
      parseInt(isoDate.slice(5, 7), 10) - 1, // Mês (ajuste para 0-indexed)
      parseInt(isoDate.slice(8, 10), 10) // Dia
    ));

    const day = date.getUTCDate().toString().padStart(2, '0');
    const month = (date.getUTCMonth() + 1).toString().padStart(2, '0');
    const year = date.getUTCFullYear().toString();
    return type === 'br' ? `${day}/${month}/${year}` : `${year}-${month}-${day}`;
  }
  return '';
}
