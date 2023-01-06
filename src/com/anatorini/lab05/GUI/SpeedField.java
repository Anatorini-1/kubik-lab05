package com.anatorini.lab05.GUI;

import com.anatorini.lab05.Main;

import javax.swing.*;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class SpeedField extends JPanel {
    private JLabel label;
    private JTextField textField;
    public SpeedField(String str, Supplier<Integer> valueSupplier, Consumer<Integer> valueConsumer){
        super();
        setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
        label = new JLabel(str);
        textField = new JTextField(valueSupplier.get().toString());
        add(label);
        add(textField);
        textField.addActionListener(e -> {
            int value = Integer.parseInt(this.textField.getText());
            valueConsumer.accept(value);
        });

    }
}
