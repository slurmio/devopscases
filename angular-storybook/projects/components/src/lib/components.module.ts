import { NgModule } from '@angular/core';
import { ComponentsComponent } from './components.component';
import { CounterComponent } from './counter/counter.component';



@NgModule({
  declarations: [
    ComponentsComponent,
    CounterComponent
  ],
  imports: [
  ],
  exports: [
    ComponentsComponent
  ]
})
export class ComponentsModule { }
