package patika.dev.definex.loancreditscore.service.creditscore;

import patika.dev.definex.loancreditscore.enums.LoanStatus;

public interface LoanStatusService {
    LoanStatus getLoanStatus(Double creditScore);
}
