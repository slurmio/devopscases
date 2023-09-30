import type { Meta, StoryObj } from '@storybook/angular';
import { CounterComponent } from './counter.component';

const meta: Meta<CounterComponent> = {
  title: 'Example/CounterComponent',
  component: CounterComponent,
  tags: ['autodocs'],
  render: (args: CounterComponent) => ({
    props: {
      ...args,
    },
  }),
};

export default meta;
type Story = StoryObj<CounterComponent>;

export const PrimaryCounter: Story = {
  args: {
    primary: true,
    value: 1,
  },
};

export const SecondaryCounter: Story = {
  args: {
    primary: false,
    value: 1,
  },
};

export const LargeCounter: Story = {
  args: {
    size: 'large',
    value: 999,
  },
};

export const SmallCounter: Story = {
  args: {
    size: 'small',
    value: 9,
  },
};
