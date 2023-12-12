export function isNearBottom(): boolean {

  const windowHeight = window.innerHeight;
  const documentHeight = document.body.clientHeight;
  const scrollPosition = window.scrollY;
  return windowHeight + scrollPosition >= documentHeight - 200;
}
