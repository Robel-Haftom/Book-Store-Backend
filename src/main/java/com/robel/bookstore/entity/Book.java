package com.robel.bookstore.entity;

import com.robel.bookstore.enums.BookFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "books",
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"bookTitle", "bookAuthor", "bookEdition", "bookPublisher"}
        ))
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;

    @NotBlank
    @Size(min = 1, max = 255)
    private String bookTitle;

    @NotBlank
    @Size(min = 1, max = 255)
    private String bookAuthor;

    @NotBlank
    @Size(min = 1, max = 255)
    private String bookLanguage;

    @NotBlank
    private String bookEdition;

    @Size(min = 1, max = 500)
    private String bookCoverImgUrl;

    @ElementCollection
    @CollectionTable(name = "book_images")
    @Column(name = "image_url")
    @Builder.Default
    private List<String> allImagesUrl = new ArrayList<>();

    @NotBlank
    @Size(min = 50, max = 2000)
    private String bookDescription;

    @NotBlank
    @Size(min = 1, max = 255)
    private String bookPublisher;

    @NonNull
    private LocalDate bookPublicationDate;

    @Enumerated(EnumType.STRING)
    private BookFormat bookFormat;

    @NotNull
    @Min(0)
    private Double bookPrice;

    @NotNull
    @Min(0)
    private Integer stockQuantity;

    @NotNull
    @Min(0)
    private Integer pageNumber;

    @Min(0)
    @Builder.Default
    private Double averageRating = 0.0;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    //relations
    @ManyToOne
    @JoinColumn(name = "category_Id")
    private Category bookCategory;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Review> bookReviews = new ArrayList<>();

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<CartItem> cartItems = new ArrayList<>();

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<OrderItem> orderItems= new ArrayList<>();

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Bookmark> bookmarks = new ArrayList<>();

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Like> likes = new ArrayList<>();

}
