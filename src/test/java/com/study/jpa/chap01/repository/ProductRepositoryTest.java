package com.study.jpa.chap01.repository;

import com.study.jpa.chap01.entity.Product;
import com.study.jpa.chap01.entity.Product.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static com.study.jpa.chap01.entity.Product.Category.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(false)
class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @BeforeEach // 테스트 돌리기 전에 실행
    void insertDummyData(){
        //given
        Product p1 = Product.builder()
                .name("아이폰")
                .category(ELECTRONIC)
                .price(10000000)
                .build();
        Product p2 = Product.builder()
                .name("짜장면")
                .category(FOOD)
                .price(8000)
                .build();
        Product p3 = Product.builder()
                .name("청바지")
                .category(FASHION)
                .price(120000)
                .build();
        Product p4 = Product.builder()
                .name("쓰레기")
                .category(FOOD)
                .build();
        //when
        productRepository.save(p1);
        productRepository.save(p2);
        productRepository.save(p3);
        productRepository.save(p4);
    }

    @Test
    @DisplayName("상품 4개를 데이터베이스에 저장해야한다")
    void testSave() {
        //given
        Product product = Product.builder()
                .name("정장")
                .price(1200000)
                .category(FASHION)
                .build();
        //when
        Product saved = productRepository.save(product);
        //then
        assertNotNull(saved);
    }

    @Test
    @DisplayName(" 상품저장")
    void testRemove() {
        //given
        long id = 2L;
        //when
        productRepository.deleteById(id);
        //then

    }

}