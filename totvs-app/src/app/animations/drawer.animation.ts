import { trigger, state, style, transition, animate } from '@angular/animations';

export const drawerAnimation = trigger('drawerState', [
  state(
    'open',
    style({
      visibility: 'visible',
      transform: 'translateX(0)',
    })
  ),
  state(
    'closed',
    style({
      visibility: 'hidden',
      transform: 'translateX(-100%)',
    })
  ),
  transition('open <=> closed', animate('250ms ease-in-out')),
]);
