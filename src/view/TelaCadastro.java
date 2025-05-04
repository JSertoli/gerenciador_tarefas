package view;

import controller.TarefaController;
import model.Tarefa;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaCadastro extends JFrame {
    public TelaCadastro(TarefaController controller, TelaPrincipal telaPrincipal, Tarefa tarefaParaEditar) {
        setTitle(tarefaParaEditar == null ? "Nova Tarefa" : "Editar Tarefa");
        setSize(300, 200);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        JTextField descricaoField = new JTextField(20);
        JTextField categoriaField = new JTextField(20);
        JButton salvarBtn = new JButton("Salvar");

        if (tarefaParaEditar != null) {
            descricaoField.setText(tarefaParaEditar.getDescricao());
            categoriaField.setText(tarefaParaEditar.getCategoria());
        }

        panel.add(new JLabel("Descrição:"));
        panel.add(descricaoField);
        panel.add(new JLabel("Categoria:"));
        panel.add(categoriaField);
        panel.add(salvarBtn);

        add(panel);

        salvarBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String desc = descricaoField.getText();
                String cat = categoriaField.getText();
                if (!desc.isEmpty() && !cat.isEmpty()) {
                    if (tarefaParaEditar == null) {
                        controller.adicionarTarefa(new Tarefa(desc, cat));
                    } else {
                        tarefaParaEditar.setDescricao(desc);
                        tarefaParaEditar.setCategoria(cat);
                    }
                    telaPrincipal.atualizarListaTarefas();
                    dispose();
                }
            }
        });
    }
}
