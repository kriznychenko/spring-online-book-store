package com.example.bookstore.repository;

import com.example.bookstore.dto.BookSearchParametersDto;
import com.example.bookstore.model.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BookSpecificationBuilder implements SpecificationBuilder<Book> {
    private final SpecificationProviderManager bookSpecificationProviderManager;

    @Override
    public Specification<Book> build(BookSearchParametersDto searchParams) {
        Specification<Book> specification = Specification.allOf();
        if (searchParams.title() != null && searchParams.title().length > 0) {
            specification = specification.and(
                    bookSpecificationProviderManager.getSpecificationProvider("title")
                            .getSpecification(searchParams.title()));
        }
        if (searchParams.author() != null && searchParams.author().length > 0) {
            specification = specification.and(
                    bookSpecificationProviderManager.getSpecificationProvider("author")
                            .getSpecification(searchParams.author()));
        }
        if (searchParams.isbn() != null && searchParams.isbn().length > 0) {
            specification = specification.and(
                    bookSpecificationProviderManager.getSpecificationProvider("isbn")
                            .getSpecification(searchParams.isbn()));
        }
        return specification;
    }
}
