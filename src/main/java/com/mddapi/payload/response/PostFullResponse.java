package com.mddapi.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import com.mddapi.entities.Comment;
import com.mddapi.entities.Post;
import com.mddapi.entities.Subject;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostFullResponse {
    private Post post;
    private List<Comment> comments;
}
