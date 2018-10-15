package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameWindow extends JFrame implements GameLog {
    final int WINDOW_WIDTH = 600;
    final int WINDOW_HEIGHT = 600;

    JTextArea gameLog;

    public GameWindow() {

        Game game = new Game(this);

        setTitle("Game");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(300, 300, WINDOW_WIDTH, WINDOW_HEIGHT);
        setResizable(false);
        getContentPane().setBackground(Color.white);
        setLayout(null);

        JPanel leftPanel = new JPanel();
        JPanel rightPanel = new JPanel();
        JPanel downPanel = new JPanel();
        leftPanel.setLayout(null);
        rightPanel.setLayout(null);
        downPanel.setLayout(null);

        leftPanel.setBounds(0, 0, 300, 270);
        rightPanel.setBounds(300, 0, 300, 270);
        downPanel.setBounds(0, 270, 600, 300);

        JComboBox cbTeam1 = new JComboBox(Hero.HeroTypes.values());
        JComboBox cbTeam2 = new JComboBox(Hero.HeroTypes.values());
        JButton buttonTeam1OK = new JButton("OK");
        JButton buttonTeam2OK = new JButton("OK");
        JTextField heroName1 = new JTextField();
        JTextField heroName2 = new JTextField();

        cbTeam1.setBounds(5,30, 100, 30);
        cbTeam2.setBounds(5,30, 100, 30);
        heroName1.setBounds(115, 30, 90, 30);
        heroName2.setBounds(115, 30, 90, 30);
        buttonTeam1OK.setBounds(215, 30, 70, 30);
        buttonTeam2OK.setBounds(215, 30, 70, 30);

        heroName1.setToolTipText("Имя героя");
        heroName2.setToolTipText("Имя героя");

        DefaultListModel<String> listTeam1Model = new DefaultListModel<>();
        DefaultListModel<String> listTeam2Model = new DefaultListModel<>();
        JList<String> listTeam1 = new JList<String>(listTeam1Model);
        JList<String> listTeam2 = new JList<String>(listTeam2Model);
        listTeam1.setBounds(5, 85, 280, 200);
        listTeam2.setBounds(5, 85, 280, 200);

        buttonTeam1OK.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if(heroName1.getText().equals("")) {
                    addToLog("Введите имя героя!");
                    return;
                }
                game.addHero(Game.Teams.TEAM1, (Hero.HeroTypes)cbTeam1.getSelectedItem(), heroName1.getText());
                listTeam1Model.addElement((Hero.HeroTypes)cbTeam1.getSelectedItem() + " " + heroName1.getText());
            }
        });

        buttonTeam2OK.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if(heroName2.getText().equals("")) {
                    addToLog("Введите имя героя!");
                    return;
                }
                game.addHero(Game.Teams.TEAM2, (Hero.HeroTypes)cbTeam2.getSelectedItem(), heroName2.getText());
                listTeam2Model.addElement((Hero.HeroTypes)cbTeam2.getSelectedItem() + " " + heroName2.getText());
            }
        });

        leftPanel.add(cbTeam1);
        rightPanel.add(cbTeam2);
        leftPanel.add(heroName1);
        rightPanel.add(heroName2);
        leftPanel.add(buttonTeam1OK);
        rightPanel.add(buttonTeam2OK);
        leftPanel.add(listTeam1);
        rightPanel.add(listTeam2);


        JPanel roundsPanel = new JPanel();
        JLabel roundsLabel = new JLabel("Количество раундов: ");
        JSpinner roundsSpinner = new JSpinner(new SpinnerNumberModel(4, 1, 10, 1));
        JButton roundsOK = new JButton("OK");

        roundsOK.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                game.startGame((Integer) roundsSpinner.getValue());
            }
        });

        roundsPanel.add(roundsLabel);
        roundsPanel.add(roundsSpinner);
        roundsPanel.add(roundsOK);
        roundsPanel.setBounds(5, 5, 250, 40);

        gameLog = new JTextArea(30,30);
        JScrollPane scrollPane = new JScrollPane(gameLog);
        scrollPane.setBounds(5, 50, 580, 230);

        downPanel.add(roundsPanel);
        downPanel.add(scrollPane);
    
        JLabel lblCommand1 = new JLabel("Команда 1");
        JLabel lblCommand2 = new JLabel("Команда 2");
        JLabel lblHeroName1 = new JLabel("Имя героя:");
        JLabel lblHeroName2 = new JLabel("Имя героя:");
        JLabel lblHeroType1 = new JLabel("Тип героя:");
        JLabel lblHeroType2 = new JLabel("Тип героя:");
        
        lblCommand1.setBounds(5, 60, 90, 20);
        lblCommand2.setBounds(5, 60, 90, 20);
        lblHeroName1.setBounds(115, 5, 90, 20);
        lblHeroName2.setBounds(115, 5, 90, 20);
        lblHeroType1.setBounds(5, 5, 90, 20);
        lblHeroType2.setBounds(5, 5, 90, 20);
        leftPanel.add(lblCommand1);
        rightPanel.add(lblCommand2);
        leftPanel.add(lblHeroName1);
        leftPanel.add(lblHeroType1);
        rightPanel.add(lblHeroName2);
        rightPanel.add(lblHeroType2);

        add(leftPanel);
        add(rightPanel);
        add(downPanel);

        setVisible(true);
    }

    public static void main(String[] args) {
        new GameWindow();
    }

    @Override
    public void clearLog() {
        gameLog.setText(null);
    }

    @Override
    public void addToLog(String text) {
        gameLog.append(text + "\n");
    }
}
