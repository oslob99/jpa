package com.study.jpa.chap05_practice.repository;

import com.study.jpa.chap05_practice.entity.Post;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(false)
class PostRepositoryTest {

    @Autowired
    PostRepository postRepository;

    @Test
    @DisplayName("bulk insert")
    void bilkInsert() {
        //given
        for (int i = 1; i < 379; i++) {
            postRepository.save(
                    Post.builder()
                            .title("하하호호"+i)
                            .content("뭘 봐!"+i)
                            .writer("야호"+i)
                            .build()
            );
        }
        //when

        //then
    }

}