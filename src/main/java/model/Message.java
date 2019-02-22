package model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {

    private int id;
    private User fromId;
    private User toId;
    private String text;
    private Date date;

}
