package com.scy.common.mogomapper;

import com.scy.common.mongodb.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface Mongomapper extends MongoRepository<Comment, String> {

}
