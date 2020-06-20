package aop.account;

public class AccountServiceImpl implements AccountService {
    @Override
    public void saveAccount(String emailPrefix) {
        System.out.println("保存账户！");
    }
}
