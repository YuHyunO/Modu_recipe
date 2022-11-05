package com.modu.domain.board;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardSearchVO {
    private long type;
    private long beginRow;
    private long endRow;
    private String sch_type;
    private String value;
    
    

}
