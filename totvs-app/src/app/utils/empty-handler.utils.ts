interface NestedObject {
  [key: string]: any;
}

export function replaceNullAndEmptyWithNA(data: NestedObject[]): NestedObject[] {
  function isNullOrArrayEmpty(value: any): boolean {
    return value === null || (Array.isArray(value) && value.length === 0);
  }

  function traverse(obj: NestedObject) {
    for (const key in obj) {
      if (obj.hasOwnProperty(key)) {
        if (typeof obj[key] === 'object' && obj[key] !== null) {
          // Caso o valor seja um objeto, chama recursivamente a função
          traverse(obj[key]);
        } else if (isNullOrArrayEmpty(obj[key])) {
          // Substitui o valor nulo ou array vazio por "N/A"
          obj[key] = "N/A";
        }
      }
    }
  }

  // Faz uma cópia do array de objetos para evitar alterações no original
  const newData = JSON.parse(JSON.stringify(data));

  // Percorre os objetos dentro do array
  for (const obj of newData) {
    traverse(obj);
  }

  return newData;
}
