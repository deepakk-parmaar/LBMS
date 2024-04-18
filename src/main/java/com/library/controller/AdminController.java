package com.library.controller;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.library.model.Book;
import com.library.model.Users;
import com.library.service.BookService;
import com.library.service.UserService;
import com.library.model.Transaction_log;
import com.library.model.BorrowedBook;
import com.library.model.Fines;
import com.library.service.TransactionService;
import com.library.service.BorrowedBookService;
import com.library.service.FinesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.context.request.WebRequest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

    private final TransactionService transactionService;
    private final BorrowedBookService borrowedBookService;
    private final FinesService finesService;

    public AdminController(TransactionService transactionService, BorrowedBookService borrowedBookService, FinesService finesService) {
        this.transactionService = transactionService;
        this.borrowedBookService = borrowedBookService;
        this.finesService = finesService;
    }

    @GetMapping("/")
    public String index() {
        return "admin/index";
    }

    @GetMapping("/loadAddBook")
    public String loadAddBook(Model model) {
        Book book = new Book();
        model.addAttribute("book", book);
        return "admin/add_book";
    }

    @GetMapping("/view_books")
    public String viewBooks(Model model) {
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        return "admin/view_books";
    }

    @GetMapping("/view_users")
    public String viewUsers(Model model) {
        List<Users> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "admin/view_users";
    }

    @GetMapping("/view_transactions")
    public String viewTransactions(Model model) {
        List<Transaction_log> transactions = transactionService.getAllTransactions();
        model.addAttribute("transactions", transactions);
        return "admin/view_transactions";
    }

    @GetMapping("/view_borrowed_books")
    public String viewBorrowedBooks(Model model) {
        List<BorrowedBook> borrowedBooks = borrowedBookService.getAllBorrowedBooks();
        model.addAttribute("borrowedBooks", borrowedBooks);
        return "admin/borrowed_list";
    }

    @PostMapping("/deleteBook")
    public String deleteBook(@RequestParam Long bookId, WebRequest request) {
        bookService.deleteBookById(bookId);
        request.setAttribute("succMsg", "Book deleted successfully", WebRequest.SCOPE_SESSION);
        return "redirect:/admin/";
    }

    @PostMapping("/saveBook")
    public String saveBook(@ModelAttribute Book book,
            @RequestParam(value = "file", required = false) MultipartFile file,
            WebRequest request) throws IOException {

        boolean existBook = bookService.existBook(book.getTitle());

        if (existBook) {
            request.setAttribute("errorMsg", "Book already exists", WebRequest.SCOPE_SESSION);
        } else {
            Book savedBook = bookService.saveBook(book);

            if (savedBook == null) {
                request.setAttribute("errorMsg", "Failed to save the book", WebRequest.SCOPE_SESSION);
            } else {
                if (file != null && !file.isEmpty()) {
                    String uploadDir = "src/main/resources/static/img/book_images";
                    Path uploadPath = Paths.get(uploadDir);

                    if (!Files.exists(uploadPath)) {
                        Files.createDirectories(uploadPath);
                    }

                    String originalFileName = file.getOriginalFilename();
                    Path filePath = uploadPath.resolve(originalFileName);
                    Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
                }

                request.setAttribute("succMsg", "Book saved successfully", WebRequest.SCOPE_SESSION);
            }
        }

        return "redirect:/admin/";
    }

    @GetMapping("/fines")
    public String fines(Model model) {
        List<Fines> fines = finesService.getAllFines();
        model.addAttribute("fines", fines);
        return "admin/fines";
    }

    @PostMapping("/fine")
    public String fine(@RequestParam("userId") Long userId, @RequestParam("amount") double fine, WebRequest request) {
        Fines fines = new Fines();
        fines.setUserid(userId);
        fines.setFine(fine);
        finesService.save(fines);
        request.setAttribute("succMsg", "Fine added successfully", WebRequest.SCOPE_SESSION);
        return "redirect:/admin/";
    }
}
