package com.example.bookstore.repository;

import com.example.bookstore.dto.BookSearchParametersDto;
import com.example.bookstore.model.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BookSpecificationBuilder implements SpecificationBuilder<Book> {
    private static final String TITLE_SPECIFICATION = "title";
    private static final String AUTHOR_SPECIFICATION = "author";
    private static final String ISBN_SPECIFICATION = "isbn";
    private final SpecificationProviderManager bookSpecificationProviderManager;

    @Override
    public Specification<Book> build(BookSearchParametersDto searchParams) {
        Specification<Book> specification = Specification.allOf();
        if (searchParams.title() != null && searchParams.title().length > 0) {
            specification = specification.and(
                    bookSpecificationProviderManager.getSpecificationProvider(TITLE_SPECIFICATION)
                            .getSpecification(searchParams.title()));
        }
        if (searchParams.author() != null && searchParams.author().length > 0) {
            specification = specification.and(
                    bookSpecificationProviderManager.getSpecificationProvider(AUTHOR_SPECIFICATION)
                            .getSpecification(searchParams.author()));
        }
        if (searchParams.isbn() != null && searchParams.isbn().length > 0) {
            specification = specification.and(
                    bookSpecificationProviderManager.getSpecificationProvider(ISBN_SPECIFICATION)
                            .getSpecification(searchParams.isbn()));
        }
        return specification;
    }
}
