package com.phaseGateTwo.cms.userauth.repository;

import com.phaseGateTwo.cms.userauth.model.Otp;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OtpRepository extends MongoRepository<Otp, String> {
}
