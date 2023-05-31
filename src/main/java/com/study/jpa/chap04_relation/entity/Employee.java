package com.study.jpa.chap04_relation.entity;

import lombok.*;

import javax.persistence.*;

@Setter @Getter
@NoArgsConstructor

// jpa연관관계 매핑에서는 연관관계 데이터는 ToString에서 제외해야한다
@ToString(exclude = {"department"})
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tbl_emp")
public class Employee {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emp_id")
    private Long id;

    @Column(name = "emp_name", nullable = false)
    private String name;

    // EAGER : 항상 무조건 조인을 수행
    // LAZY : 필요헌 경우에만 조인을 수행
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dept_id")
    private Department department;


}
