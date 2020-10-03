package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

  private final AuthorRepository authorRepository;
  private final BookRepository bookRepository;
  private final PublisherRepository publisherRepository;

  public BootstrapData(
      AuthorRepository authorRepository,
      BookRepository bookRepository,
      PublisherRepository publisherRepository) {
    this.authorRepository = authorRepository;
    this.bookRepository = bookRepository;
    this.publisherRepository = publisherRepository;
  }

  @Override
  public void run(String... args) throws Exception {

    Publisher pub1 = new Publisher("pub1", "addr1", "city1", "state1", "zip1");
    publisherRepository.save(pub1);

    Author eric = new Author("Eric", "Evans");
    Book ddd = new Book("Domain Driven Design", "978-0321125217");

    eric.getBooks().add(ddd);
    ddd.getAuthors().add(eric);

    bookRepository.save(ddd);
    authorRepository.save(eric);

    Author rod = new Author("Rod", "Johnson");
    Book noEJB = new Book("J2EE Development without EJB", "978-0321125218");

    rod.getBooks().add(noEJB);
    noEJB.getAuthors().add(rod);

    noEJB.setPublisher(pub1);
    pub1.getBooks().add(noEJB);

    ddd.setPublisher(pub1);
    pub1.getBooks().add(ddd);

    bookRepository.save(noEJB);
    authorRepository.save(rod);
    publisherRepository.save(pub1);

    ddd.setPublisher(pub1);
    pub1.getBooks().add(ddd);
    System.out.println("Started in Bootstrap");
    System.out.println("Number of Books: " + bookRepository.count());
    System.out.println("Number of Publishers: " + publisherRepository.count());
    System.out.println(pub1);
    System.out.println("Publisher Number of Books: " + pub1.getBooks().size());
  }

}
