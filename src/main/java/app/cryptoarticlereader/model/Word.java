package app.cryptoarticlereader.model;

import javax.persistence.*;

@Table
@Entity
public class Word {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "value")
    private String value;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private TypeOfWord typeOfWord;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public TypeOfWord getTypeOfWord() {
        return typeOfWord;
    }

    public void setTypeOfWord(TypeOfWord typeOfWord) {
        this.typeOfWord = typeOfWord;
    }
}


