package com.robel.bookstore.controller.user;

import com.robel.bookstore.dto.LikeCreateDTO;
import com.robel.bookstore.dto.LikeResponseDTO;
import com.robel.bookstore.service.LikeServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users/likes")
public class LikeController {

    @Autowired
    private LikeServices likeServices;

    @PostMapping
    public ResponseEntity<LikeResponseDTO> addLike(@Valid @RequestBody LikeCreateDTO likeCreateDTO){
        return ResponseEntity.ok().body(likeServices.likeBook(likeCreateDTO));
    }

    @DeleteMapping("{likeId}")
    public ResponseEntity<String> removeLike(@PathVariable("likeId") Long likeId){
        likeServices.removeLike(likeId);
        return ResponseEntity.ok().body("Like removed successfully");
    }
}
