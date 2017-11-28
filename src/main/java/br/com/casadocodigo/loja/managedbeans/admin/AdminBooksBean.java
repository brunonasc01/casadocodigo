package br.com.casadocodigo.loja.managedbeans.admin;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.servlet.http.Part;
import javax.transaction.Transactional;

import br.com.casadocodigo.loja.daos.AuthorDAO;
import br.com.casadocodigo.loja.daos.BookDAO;
import br.com.casadocodigo.loja.infra.FileSaver;
import br.com.casadocodigo.loja.infra.MessagesHelper;
import br.com.casadocodigo.loja.models.Author;
import br.com.casadocodigo.loja.models.Book;

@Model
public class AdminBooksBean {

	private Part summary;

	public void setSummary(Part summary) {
		this.summary = summary;
	}

	public Part getSummary() {
		return summary;
	}

	private Book product = new Book();

	private List<Author> authors = new ArrayList<Author>();

	private List<Integer> selectedAuthorsIds = new ArrayList<>();

	@Inject
	private MessagesHelper messagesHelper;

	@Inject
	private BookDAO bookDAO;

	@Inject
	private AuthorDAO authorDAO;

	@Inject
	private FileSaver fileSaver;

	@Transactional
	public String save(){
		//System.out.println(extractFilename(summary.getHeader("content-disposition")));
		String summaryPath = fileSaver.write("summaries", summary);

		product.setSummaryPath(summaryPath);
		bookDAO.save(product);

		messagesHelper.addFlash(new FacesMessage("Livro gravado com sucesso"));

		return "/livros/lista?faces-redirect=true";
	}

	@PostConstruct
	public void loadObjects(){
		this.authors = authorDAO.list();
	}

	public Book getProduct() {
		return product;
	}

	public List<Integer> getSelectedAuthorsIds() {
		return selectedAuthorsIds;
	}

	public void setSelectedAuthorsIds(List<Integer> selectedAuthorsIds) {
		this.selectedAuthorsIds = selectedAuthorsIds;
	}

	public List<Author> getAuthors() {
		return authors;
		}
}
