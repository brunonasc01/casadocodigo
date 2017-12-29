package br.com.casadocodigo.loja.resources;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.casadocodigo.loja.daos.BookDAO;
import br.com.casadocodigo.loja.models.Book;

@Path("books")
@Stateful
public class BooksResource {

	@PersistenceContext(type=PersistenceContextType.EXTENDED)
	private EntityManager entityManager;

	private BookDAO bookDAO;

	@PostConstruct
	private void loadDAO(){
		this.bookDAO = new BookDAO(entityManager);
	}

	@GET
//	@Path("json")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<Book> lastBooksJson(){
		return bookDAO.lastReleases();
	}

//	@GET
//	@Produces(MediaType.APPLICATION_XML)
//	@Path("xml")
//	@Wrapped(element="books")
//	public List<Book> lastBooks(){
//		return bookDAO.lastReleases();
//	}
}
