package br.com.casadocodigo.loja.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Cacheable
public class Book {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@NotBlank @NotNull
	private String title;

	@NotBlank @NotNull @Length(min = 10)
	private String description;

	@Min(50)
	private int numberOfPages;

	@DecimalMin("20")
	private BigDecimal price;

	@ManyToMany
	@Size(min = 1)
	@NotNull
	private List<Author> authors = new ArrayList<>();

	@NotNull
	@Future
	private Calendar releaseDate;

	private String coverPath;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getNumberOfPages() {
		return numberOfPages;
	}
	public void setNumberOfPages(int numberOfPages) {
		this.numberOfPages = numberOfPages;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public void add(Author author) {
		authors.add(author);
	}
	public List<Author> getAuthors() {
		return authors;
	}
	public Calendar getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(Calendar releaseDate) {
		this.releaseDate = releaseDate;
	}
	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}
	public String getCoverPath() {
		return coverPath;
	}
	public void setCoverPath(String coverPath) {
		this.coverPath = coverPath;
	}
	@Override
	public String toString() {
		return "Product [title=" + title + ", description=" + description + "]";
	}

}
