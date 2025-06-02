package com.robel.bookstore.service.impl;

import com.robel.bookstore.dto.BookCreateDTO;
import com.robel.bookstore.dto.BookResponseDTO;
import com.robel.bookstore.entity.Book;
import com.robel.bookstore.entity.Category;
import com.robel.bookstore.enums.BookFormat;
import com.robel.bookstore.exception.BookExistException;
import com.robel.bookstore.exception.BookNotFoundException;
import com.robel.bookstore.exception.CategoryNotFoundException;
import com.robel.bookstore.mapper.BookMapper;
import com.robel.bookstore.repository.BookRepository;
import com.robel.bookstore.repository.CategoryRepository;
import com.robel.bookstore.service.BookServices;
import com.robel.bookstore.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class BookServicesImpl implements BookServices {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private FileService fileService;

    @Value("${images.book.image}")
    String path;

    @Value("${base.url}")
    String baseUrl;
    @Override
    public BookResponseDTO addBook(BookCreateDTO bookCreateDTO, List<MultipartFile> allImg) throws IOException {
        bookRepository.findByBookTitleAndBookAuthorAndBookPublisher(
                bookCreateDTO.getBookTitle(),
                bookCreateDTO.getBookAuthor(),
                bookCreateDTO.getBookPublisher()
        ).ifPresent(book -> {
            throw new BookExistException("Book already exists");
        });
        Category category = categoryRepository.findById(bookCreateDTO.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Book book = BookMapper.mapToBook(bookCreateDTO);
        book.setBookCategory(category);
        book.setCreatedAt(LocalDateTime.now());
        book.setUpdatedAt(LocalDateTime.now());
        switch(bookCreateDTO.getBookEdition()){
            case 1 -> book.setBookEdition("1st Edition");
            case 2 -> book.setBookEdition("2nd Edition");
            case 3 -> book.setBookEdition("3rd Edition");
            default -> book.setBookEdition(bookCreateDTO.getBookEdition() +"th Edition");
        }

        Book savedBook = bookRepository.save(book);

        String uniqueName = ( savedBook.getBookTitle()
                                + savedBook.getBookAuthor()
                                + savedBook.getBookEdition()
                                + savedBook.getBookPublisher()
                                + savedBook.getBookPublicationDate()
                            ).replaceAll("[^a-zA-Z0-9]", "_").toLowerCase();
        List<String> allImageNames = fileService.uploadBookImage(allImg, path, uniqueName);
        List<String> allImageURL = allImageNames.stream().map(
                imageName -> baseUrl + "books/" + uniqueName + "/" + imageName
        ).toList();

        savedBook.setAllImagesUrl(new ArrayList<>(allImageURL));
        savedBook.setBookCoverImgUrl(allImageURL.get(0));

        Book bookWithImages = bookRepository.save(savedBook);

        return BookMapper.mapToBookResponseDTO(bookWithImages);
    }

    @Override
    public BookResponseDTO getBookById(Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException("Book not found"));
        return BookMapper.mapToBookResponseDTO(book);
    }

    @Override
    public List<BookResponseDTO> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books.stream().map(BookMapper::mapToBookResponseDTO).toList();
    }

    @Override
    public BookResponseDTO updateBook(Long bookId, BookCreateDTO bookCreateDTO) {
        bookRepository.findByBookTitleAndBookAuthorAndBookPublisher(
                bookCreateDTO.getBookTitle(),
                bookCreateDTO.getBookAuthor(),
                bookCreateDTO.getBookPublisher()
        ).ifPresent(book -> {
            throw new BookExistException("Book already exists");
        });
        Book existingBook = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException("Book not found"));
        Category category = categoryRepository.findById(bookCreateDTO.getCategoryId())
                .orElseThrow(() -> new CategoryNotFoundException("Category not found"));

        boolean isUpdated = false;

        if(Objects.nonNull(bookCreateDTO.getBookTitle()) && !"".equalsIgnoreCase(bookCreateDTO.getBookTitle())){
            existingBook.setBookTitle(bookCreateDTO.getBookTitle());
            isUpdated = true;
        }
        if(Objects.nonNull(bookCreateDTO.getBookAuthor()) && !"".equalsIgnoreCase(bookCreateDTO.getBookAuthor())){
            existingBook.setBookAuthor(bookCreateDTO.getBookAuthor());
            isUpdated = true;
        }
        if(Objects.nonNull(bookCreateDTO.getBookEdition()) && !"".equalsIgnoreCase(bookCreateDTO.getBookEdition().toString())){
            switch(bookCreateDTO.getBookEdition()){
                case 1 -> existingBook.setBookEdition("1st Edition");
                case 2 -> existingBook.setBookEdition("2nd Edition");
                case 3 -> existingBook.setBookEdition("3rd Edition");
                default -> existingBook.setBookEdition(bookCreateDTO.getBookEdition() +"th Edition");
            }
            isUpdated = true;
        }
        if(Objects.nonNull(bookCreateDTO.getBookPublisher()) && !"".equalsIgnoreCase(bookCreateDTO.getBookPublisher())){
            existingBook.setBookPublisher(bookCreateDTO.getBookPublisher());
            isUpdated = true;
        }
        if(Objects.nonNull(bookCreateDTO.getBookLanguage()) && !"".equalsIgnoreCase(bookCreateDTO.getBookLanguage())){
            existingBook.setBookLanguage(bookCreateDTO.getBookLanguage());
            isUpdated = true;
        }
        if(Objects.nonNull(bookCreateDTO.getBookDescription()) && !"".equalsIgnoreCase(bookCreateDTO.getBookDescription())){
            existingBook.setBookDescription(bookCreateDTO.getBookDescription());
            isUpdated = true;
        }
        if(Objects.nonNull(bookCreateDTO.getBookPublicationDate()) && !"".equalsIgnoreCase(bookCreateDTO.getBookPublicationDate().toString())){
            existingBook.setBookPublicationDate(LocalDate.parse(bookCreateDTO.getBookPublicationDate()));
            isUpdated = true;
        }
        if(Objects.nonNull(bookCreateDTO.getBookFormat()) && !"".equalsIgnoreCase(bookCreateDTO.getBookFormat())){
            existingBook.setBookFormat(BookFormat.valueOf(bookCreateDTO.getBookFormat().toUpperCase()));
            isUpdated = true;
        }
        if(Objects.nonNull(bookCreateDTO.getBookPrice()) && !"".equalsIgnoreCase(bookCreateDTO.getBookPrice().toString())){
            existingBook.setBookPrice(bookCreateDTO.getBookPrice());
            isUpdated = true;
        }
        if(Objects.nonNull(bookCreateDTO.getStockQuantity()) && !"".equalsIgnoreCase(bookCreateDTO.getStockQuantity().toString())){
            existingBook.setStockQuantity(bookCreateDTO.getStockQuantity());
            isUpdated = true;
        }
        if(Objects.nonNull(bookCreateDTO.getCategoryId()) && !"".equalsIgnoreCase(bookCreateDTO.getCategoryId().toString())){
            Category category1 = categoryRepository.findById(bookCreateDTO.getCategoryId())
                    .orElseThrow(() -> new CategoryNotFoundException("Category not found"));
            existingBook.setBookCategory(category1);
            isUpdated = true;
        }
        if(isUpdated){
            existingBook.setUpdatedAt(LocalDateTime.now());
            Book updatedBook = bookRepository.save(existingBook);
            return BookMapper.mapToBookResponseDTO(updatedBook);
        }

        return BookMapper.mapToBookResponseDTO(existingBook);
    }

    @Override
    public void deleteBook(int bookId) throws IOException {
        Book book = bookRepository.findById((long) bookId)
                .orElseThrow(() -> new BookNotFoundException("Book not found"));
        bookRepository.delete(book);
        String uniqueName = ( book.getBookTitle()
                + book.getBookAuthor()
                + book.getBookEdition()
                + book.getBookPublisher()
                + book.getBookPublicationDate()
        ).replaceAll("[^a-zA-Z0-9]", "_").toLowerCase();
        fileService.deleteBookImage(path, uniqueName);
    }
}
