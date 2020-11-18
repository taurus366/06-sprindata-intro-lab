package course.springdata.intro.init;

import course.springdata.intro.entity.Account;
import course.springdata.intro.entity.User;
import course.springdata.intro.exception.InvalidAccountOperationException;
import course.springdata.intro.exception.NonExistingEntityException;
import course.springdata.intro.service.AccountService;
import course.springdata.intro.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Slf4j
public class ConsoleRunner implements CommandLineRunner {
    @Autowired
    private UserService userService;
    @Autowired
    private AccountService accountService;


    @Override
    public void run(String... args) throws Exception {
        User user1 = new User("ivan Petrov",26);
        Account account1 = new Account(new BigDecimal(1500));

        User user2 = new User("Ali Zinal",26);
        Account account2 = new Account(new BigDecimal(1500));

      user1 = userService.register(user1);
        accountService.createUserAccount(user1, account1);
        user2 = userService.register(user2);
        accountService.createUserAccount(user2, account2);

        try {
            accountService.withdrawMoney(new BigDecimal(2000), account1.getId());
            accountService.depositMoney(new BigDecimal(200), account1.getId());
            accountService.getAllAccounts().forEach(System.out::println);
        }catch (NonExistingEntityException | InvalidAccountOperationException ex){
            log.error(String.format("Error executing operation for account: %s : %s",account1,ex.getMessage()));
        }

//        accountService.withdrawMoney(new BigDecimal(1000), account1.getId());
//        //accountService.transferMoney();
//        accountService.depositMoney(new BigDecimal(200), account1.getId());

      //  accountService.transferMoney(new BigDecimal(1500), user1.getId(), user2.getId());


        //accountService.getAllAccounts().forEach(System.out::println);
    }
}
