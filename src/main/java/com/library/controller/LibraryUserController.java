package com.library.controller;
import com.library.model.Transaction_log;
import com.library.model.Book;
import com.library.model.BoughtBook;
import com.library.model.Users;
import com.library.service.BookService;
import com.library.service.UserService;
import com.library.model.BorrowedBook;
import com.library.service.BorrowedBookService;
import com.library.repository.TransactionRepository;
import org.springframework.web.bind.annotation.RequestMethod;
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
import java.util.Date;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.time.LocalDate; // Added import for LocalDate
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import com.library.UserSingleton;

@Controller
@RequestMapping("/libraryuser")
public class LibraryUserController {

    //Facade
    private final BorrowedBookService borrowedBookService;
    private final BookService bookService;
    private final TransactionRepository transactionRepository;
    private UserService userService;
    private final UserSingleton userSingleton;

    @Autowired
    public LibraryUserController(BorrowedBookService borrowedBookService, BookService bookService,
            TransactionRepository transactionRepository, UserService userService, UserSingleton userSingleton) {
        this.borrowedBookService = borrowedBookService;
        this.bookService = bookService;
        this.userService = userService;
        this.userSingleton = userSingleton;
        this.transactionRepository = transactionRepository;
    }
    
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    public void borrowBook(Long bookId, Long userId) {
        BorrowedBook borrowedBook = new BorrowedBook(bookId, userId, LocalDate.now(), LocalDate.now().plusDays(7));
        borrowedBookService.save(borrowedBook);
    }

    public void saveTransaction(Long bookId, Long userId, int quantity, String paymentMethod) {
        double totalPrice = 1000 * quantity; // Assuming each book costs 1000

        // Create a TransactionLog object
        Transaction_log transaction_log = new Transaction_log();
        transaction_log.setBook_id(bookId);
        transaction_log.setUser_id(userId);
        transaction_log.setTransaction_date(LocalDate.now().toString());
        transaction_log.setNumber_of_books(quantity);
        transaction_log.setPayment_method(paymentMethod);
        transaction_log.setTotal_amount(totalPrice);
        transaction_log.setTransaction_info("Transaction for book id " + bookId);

        // Save the transaction log to the database
        transactionRepository.save(transaction_log);
    }

    public List<BorrowedBook> getBorrowedBooksByUserId(int userId) {
        return borrowedBookService.getBooksbyUserId(userId);
    }

    @GetMapping("/")
    public String index() {
        return "libraryuser/libraryUser_index";
    }

    @GetMapping("/borrow_book")
    public String borrow_book(Model model) {
        List<Book> books = getAllBooks();
        model.addAttribute("books", books);
        return "libraryuser/borrow_book";
    }

    @PostMapping("/borrow_book")
    public String borrowBook(@RequestParam("bookId") Long bookId, Model model) {
        Long userId = Long.parseLong(userSingleton.getUserId());
        borrowBook(bookId, userId);
        model.addAttribute("successMessage", "Book borrowed successfully!");
        return "redirect:/libraryuser/";
    }

    @GetMapping("/buy_book")
    public String buy_book(Model model) {
        List<Book> books = getAllBooks();
        model.addAttribute("books", books);
        return "libraryuser/buy_book";
    }

    @GetMapping("/buy_book_form")
    public String buyBookForm(@RequestParam("bookId") String bookId, Model model) {
        String[] book= {bookId};
        model.addAttribute("book", book);
        return "libraryuser/buy_book_form";
    }

    @PostMapping("/transaction")
    public String transaction(@RequestParam("bookId") String bookIdstr, @RequestParam("quantity") int quantity,
            @RequestParam("paymentMethod") String paymentMethod, Model model) {
        Long bookId = Long.parseLong(bookIdstr);
        Long userId = Long.parseLong(userSingleton.getUserId());
        saveTransaction(bookId, userId, quantity, paymentMethod);
        // Redirect to some page after successful transaction
        return "redirect:/libraryuser/";
    }

    @GetMapping("/view_books")
    public String viewBooks(Model model) {
        List<Book> books = getAllBooks();
        model.addAttribute("books", books);
        return "libraryuser/view_books";
    }

    @GetMapping("/returnBook")
    public String return_book(Model model) {
        int userId = Integer.parseInt(userSingleton.getUserId());
        List<BorrowedBook> borrowedBooks = borrowedBookService.getBooksbyUserId(userId);
        model.addAttribute("borrowed_book", borrowedBooks);
        return "libraryuser/returnBook";
    }

}
