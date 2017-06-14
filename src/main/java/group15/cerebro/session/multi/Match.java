package group15.cerebro.session.multi;

import group15.cerebro.entities.Question;
import group15.cerebro.entities.Usr;

import java.util.Timer;
import java.util.TimerTask;

public class Match {
    private Usr player1;
    private Usr player2;
    public static final int TOTAL = 10;
    private int remainingQuestions;

    private boolean pinged1;
    private boolean pinged2;

    private TimerTask timerTask;
    private Timer timer;
    private Question question;

    public Match(Usr player1, Usr player2) {
        this.player1 = player1;
        this.player2 = player2;
        pinged1 = false;
        pinged2 = false;
        question = null;
        remainingQuestions = TOTAL;

        timerTask = new TimerTask() {
            @Override
            public void run() {
                pinged1 = false;
                pinged2 = false;
            }
        };

        timer = new Timer("timer");
        timer.scheduleAtFixedRate(timerTask, 30, 100);
    }

    public synchronized Usr getPlayer1() {
        return player1;
    }

    public synchronized Usr getPlayer2() {
        return player2;
    }

    public synchronized boolean ping(Usr user) {
        if (user.getLogin().equals(player1.getLogin())) {
            pinged1 = true;
        }
        if (user.getLogin().equals(player1.getLogin())) {
            pinged2 = true;
        }
        return pinged1 && pinged2;
    }

    public synchronized Question getQuestion() {
        return question;
    }

    public synchronized void setQuestion(Question question) {
        this.question = question;
    }

    public synchronized void play() {
        remainingQuestions--;
    }

    public synchronized int getRemainingQuestions() {
        return remainingQuestions;
    }

    public synchronized void checkNullSetQuestion(Question randomQuestion) {
        if (question == null) {
            question = randomQuestion;
        }
    }
}
