package com.epam.jwd.text;

import com.epam.jwd.parser.Parser;

import java.util.ArrayList;
import java.util.List;

public class Text{

    private List<Sentence> sentences;

    public Text(String textSource){
        initialize(textSource);
    }

    public Text(List<Sentence> listSource){
        sentences = listSource;
    }

    public List<Sentence> getAsSentences(){
        return sentences;
    }

    public Text copy() {
        List<Sentence> newSentences = new ArrayList<>();
        sentences.forEach(sentence -> newSentences.add(sentence.copy()));
        return new Text(newSentences);
    }

    public void addSentence(Sentence source){
        sentences.add(source);
    }

    public void addSentence(String source){
        sentences.add(new Sentence(source));
    }

    public void addSentence(Sentence source, int index){
        sentences.add(index, source);
    }

    public void addSentence(String source, int index){
        sentences.add(index, new Sentence(source));
    }

    public Sentence getFirstSentence(){
        return sentences.get(0);
    }

    public Sentence getLastSentence(){
        return sentences.get(sentences.size()-1);
    }

    public Sentence getSentence(int index){
        return sentences.get(index);
    }

    private void initialize(String textSource){
        sentences = Parser.textToSentenceList(textSource);
    }

    @Override
    public int hashCode() {
        return sentences.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Text){
            return sentences.equals(((Text) obj).getAsSentences());
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sentences.forEach(sentence -> {
            sb.append(sentence);
            if (!(sentence.contains(".") || sentence.contains(":"))) {
                sb.append(".");
            }
            sb.append("\n");
        });
        return sb.toString();
    }
}
