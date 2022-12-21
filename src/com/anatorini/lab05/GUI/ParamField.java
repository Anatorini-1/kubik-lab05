package com.anatorini.lab05.GUI;

import javax.swing.*;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class ParamField extends JPanel {
    private JLabel label;
    private JTextField textField;
    private JSlider slider;
    public ParamField(String label, Supplier<Integer> valueSupplier, Consumer<Integer> valueConsumer, int min, int max, int step){
        this.label = new JLabel();
        this.textField = new JTextField();
        this.slider = new JSlider();

        this.label.setText(label);
        this.textField.setText(valueSupplier.get().toString());
        this.slider.setValue(valueSupplier.get());
        this.slider.setMinimum(min);
        this.slider.setMaximum(max);
        this.slider.setMajorTickSpacing(step);
        this.slider.setPaintTicks(true);
        this.slider.setPaintLabels(true);
        this.slider.addChangeListener(e -> {
            valueConsumer.accept(this.slider.getValue());
            this.textField.setText(this.slider.getValue()+"");
        });

        this.textField.addActionListener(e -> {
            int value = Integer.parseInt(this.textField.getText());
            valueConsumer.accept(value);
            this.slider.setValue(value);
        });
        add(this.label);
        add(this.textField);
        add(this.slider);
    }
}
