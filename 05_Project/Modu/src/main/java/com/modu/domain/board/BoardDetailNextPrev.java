package com.modu.domain.board;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BoardDetailNextPrev {
    private long id;
    private String mEmail;
    private String mNickname;
    private String profileImg;
    private int postType;
    private String title;
    private String content;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yy/ MM/dd hh:mm", timezone="Asia/Seoul")
    private Date postDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yy/ MM/dd hh:mm", timezone="Asia/Seoul")
    private Date updateDate;
    private long hits;
    private long reply;
    private int files;
    private long prevId;
    private long nextId;
   
}
