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

import java.util.List;
import java.util.Optional;

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

    @Test
    @DisplayName("전체조회")
    void testSelectAll() {
        //given
        List<Product> productList = productRepository.findAll();
        //when
        System.out.println("productList = " + productList);
        //then
    }

    @Test
    @DisplayName("개별 조회")
    void testFindOnen() {
        //given
        long id = 3L;
        //when
        Optional<Product> product = productRepository.findById(id);
        //then
        product.ifPresent(product1 -> {
            product1.getId();
        });
    }

    @Test
    @DisplayName("상품의 이름과 가격을 변경해야한다")
    void testModify() {
        //given
        long id = 2L;
        String newName = "볶음밥";
        int newPrice = 5000;
        //when
        // jpa에서 update는 따로 메서드를 지원하지 않고
        // 조회한 후 setter로 변경하면 자동으로 update문이 나간다
        // 변경 후 다시 save를 호출해야한다
        Optional<Product> product = productRepository.findById(id);
        product.ifPresent(p ->{
            p.setName(newName);
            p.setPrice(newPrice);

            productRepository.save(p);
        });
        //then
    }

}