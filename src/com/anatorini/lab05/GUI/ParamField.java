package com.anatorini.lab05.GUI;

import com.anatorini.lab05.Main;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class ParamField extends JPanel {
    private JLabel label;
    private JTextField textField;
    private JSlider slider;

    private Boolean x;
    public ParamField(String label, Supplier<Integer> valueSupplier, Consumer<Integer> valueConsumer, int min, Supplier<Integer> max, int step){
        this.label = new JLabel();
        this.textField = new JTextField();
        this.slider = new JSlider();
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        this.label.setText(label);
        this.textField.setText(valueSupplier.get().toString());
        this.slider.setValue(valueSupplier.get());
        this.slider.setMinimum(min);
        this.slider.setMaximum(max.get());
        this.slider.setMajorTickSpacing(step);
       // this.slider.setPaintTicks(true);
       // this.slider.setPaintLabels(true);
        this.slider.addChangeListener(e -> {

            if((this.slider.getValueIsAdjusting())) this.slider.setValue(this.slider.getValue() +  (Math.abs(valueSupplier.get() - this.slider.getValue()) % step));
            this.textField.setText(this.slider.getValue() +  (Math.abs(valueSupplier.get() - this.slider.getValue()) % step)+"");
            if(!Main.isRunning){
                valueConsumer.accept(this.slider.getValue());
            }
            this.slider.setMaximum(max.get());
        });
        this.textField.addActionListener(e -> {
            int value = Integer.parseInt(this.textField.getText());
            if(value > max.get()) value = max.get();
            value += (Math.abs(valueSupplier.get() - value) % step);
            this.slider.setValue(value);
            if(!Main.isRunning){
                valueConsumer.accept(this.slider.getValue());
            }
        });
        c.gridx=0;
        c.gridy=0;
        c.weightx=1;
        c.weighty=0;
        c.fill=GridBagConstraints.HORIZONTAL;
        add(this.label,c);
        c.gridy++;
        add(this.textField,c);
        c.gridy++;
        add(this.slider,c);
    }

    public int getValue(){
        return slider.getValue();
    }
}
