
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TodoApp {

    private JFrame frame;
    private JTextField taskInput;
    private DefaultListModel<String> taskListModel;
    private JList<String> taskList;

    public TodoApp() {
        // フレームの設定
        frame = new JFrame("TODOアプリ");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        // タスク入力フィールド
        taskInput = new JTextField();
        frame.add(taskInput, BorderLayout.NORTH);

        // タスクリスト
        taskListModel = new DefaultListModel<>();
        taskList = new JList<>(taskListModel);
        frame.add(new JScrollPane(taskList), BorderLayout.CENTER);

        // ボタンパネル
        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("追加");
        JButton removeButton = new JButton("削除");

        // ボタンアクション
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String task = taskInput.getText().trim();
                if (!task.isEmpty()) {
                    taskListModel.addElement(task);
                    taskInput.setText("");
                } else {
                    JOptionPane.showMessageDialog(frame, "タスクを入力してください。", "エラー", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = taskList.getSelectedIndex();
                if (selectedIndex != -1) {
                    taskListModel.remove(selectedIndex);
                } else {
                    JOptionPane.showMessageDialog(frame, "削除するタスクを選択してください。", "エラー", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        // フレームを表示
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TodoApp());
    }
}
