package view;

import controller.TarefaController;
import model.Tarefa;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class TelaPrincipal extends JFrame {
    private TarefaController controller = new TarefaController();
    private JPanel tarefaPanel = new JPanel();
    private JScrollPane scrollPane;

    public TelaPrincipal() {
        setTitle("Lista de Tarefas");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Tarefa");
        JMenuItem adicionar = new JMenuItem("Nova Tarefa");
        menu.add(adicionar);
        menuBar.add(menu);
        setJMenuBar(menuBar);

        tarefaPanel.setLayout(new BoxLayout(tarefaPanel, BoxLayout.Y_AXIS));
        scrollPane = new JScrollPane(tarefaPanel);
        add(scrollPane, BorderLayout.CENTER);

        adicionar.addActionListener(e -> {
            TelaCadastro telaCadastro = new TelaCadastro(controller, this, null);
            telaCadastro.setVisible(true);
        });

        atualizarListaTarefas();
    }

    public void atualizarListaTarefas() {
    tarefaPanel.removeAll();
    List<Tarefa> tarefas = controller.listarTarefas();

    for (int i = 0; i < tarefas.size(); i++) {
        Tarefa tarefa = tarefas.get(i);

        JPanel linha = new JPanel();
        linha.setLayout(new BoxLayout(linha, BoxLayout.X_AXIS));
        linha.setAlignmentX(Component.LEFT_ALIGNMENT);

        JCheckBox checkBox = new JCheckBox();
        checkBox.setSelected(tarefa.isConcluida());

        JLabel label = new JLabel(tarefa.toString());
        label.setPreferredSize(new Dimension(200, 30)); // Tamanho fixo para alinhar

        JButton editar = new JButton("Editar");
        JButton excluir = new JButton("Excluir");

        final int index = i;

        // Listeners para ouvirem as atividades e alterá-las
        checkBox.addActionListener(e -> {
            tarefa.setConcluida(checkBox.isSelected());
            atualizarListaTarefas();
        });

        editar.addActionListener(e -> {
            TelaCadastro telaCadastro = new TelaCadastro(controller, this, tarefa);
            telaCadastro.setVisible(true);
        });

        excluir.addActionListener(e -> {
            controller.excluirTarefa(index);
            atualizarListaTarefas();
        });

        linha.add(Box.createHorizontalStrut(10)); // Espaçamento
        linha.add(checkBox);
        linha.add(Box.createHorizontalStrut(10));
        linha.add(label);
        linha.add(Box.createHorizontalGlue()); // Empurra os botões para a direita
        linha.add(editar);
        linha.add(Box.createHorizontalStrut(5));
        linha.add(excluir);
        linha.add(Box.createHorizontalStrut(10));

        tarefaPanel.add(linha);
    }

    tarefaPanel.revalidate();
    tarefaPanel.repaint();
}

}
