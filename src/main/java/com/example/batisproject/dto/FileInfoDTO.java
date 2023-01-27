package com.example.batisproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileInfoDTO {
    private Long id;
    private String fileName;    // file_name
    private String saveFileName; // save_file_name
    private String contentType; // content_type
    private boolean deleteFlag; // delete_flag

    private LocalDateTime createDate;   // create_date
    private LocalDateTime modifyDate;   // modify_date
}
