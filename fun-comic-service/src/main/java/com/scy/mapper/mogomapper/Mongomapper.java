package com.scy.mapper.mogomapper;

import com.scy.pojo.mongodb.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface Mongomapper extends MongoRepository<Comment, String> {

}
