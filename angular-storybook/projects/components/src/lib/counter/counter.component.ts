import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'lib-counter',
  templateUrl: './counter.component.html',
  styleUrls: ['./counter.component.css']
})
export class CounterComponent {
  @Input() prefix = 'lib-counter';
  @Input() primary = true;
  @Input() size : 'small' | 'medium' | 'large' = 'medium';
  @Input() testid = '';
  @Input() type = '';
  @Input() disabled = false;
  @Input() value = 0;
  @Output() increase = new EventEmitter<number>();

  public get classes(): string[] {
    return [this.prefix, `${this.prefix}--${this.size}`, `${this.prefix}--${this.primary ? 'primary' : 'secondary'}`];
  }

  onClick($event: Event) {
    $event.preventDefault();
    this.value++;
    console.log('click');
    this.increase.emit(this.value);
  }
}
