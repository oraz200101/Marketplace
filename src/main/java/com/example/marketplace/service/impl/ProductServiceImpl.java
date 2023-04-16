package com.example.marketplace.service.impl;

import com.example.marketplace.exception.domain.ProductNotFoundByIdException;
import com.example.marketplace.model.dto.CommentCreateDto;
import com.example.marketplace.model.dto.ProductCreateDto;
import com.example.marketplace.model.dto.ProductUpdateDto;
import com.example.marketplace.model.dto.SearchRequestDto;
import com.example.marketplace.model.entity.*;
import com.example.marketplace.repository.CategoryRepository;
import com.example.marketplace.repository.CommentRepository;
import com.example.marketplace.repository.ProductRepository;
import com.example.marketplace.repository.UserRepository;
import com.example.marketplace.service.CommentService;
import com.example.marketplace.service.ProductService;
import com.example.marketplace.specification.SearchSpecification;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService, CommentService {
    private final ProductRepository productRepository;

    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final BucketServiceImpl bucketService;
    private final CommentRepository commentRepository;
    private final ModelMapper modelMapper;
    @Autowired
    public ProductServiceImpl(ProductRepository productRepository,CategoryRepository categoryRepository,
                              UserRepository userRepository,BucketServiceImpl bucketService,
    CommentRepository  commentRepository
    ) {
        this.productRepository = productRepository;
        this.categoryRepository=categoryRepository;
        this.userRepository=userRepository;
        this.bucketService=bucketService;
        this.commentRepository=commentRepository;
        this.modelMapper=new ModelMapper();

    }

    @Override
    public Page<Product> getAll(Pageable pageable,int pageNumber) {
        return productRepository.findAll(pageable.withPage(pageNumber));
    }

    @Override
    public Page<Product> searchWithFilter( SearchRequestDto request, Pageable pageable,int pageNumber) {
        SearchSpecification<Product> specification=new SearchSpecification<>(request);
        return productRepository.findAll(specification,pageable.withPage(pageNumber));
    }

    @Override
    public String addToUserBucket(Long productId, String username) {
        User user= userRepository.getUserByPhoneNumber(username);
        if(user==null){
            throw new RuntimeException("user not found");
        }
        Bucket bucket=user.getBucket();
        if (bucket==null){
            Bucket newBucket=bucketService.createBucket(user, Collections.singletonList(productId));
            user.setBucket(newBucket);
            userRepository.save(user);
        }

        return "addProductSuccess";
    }

    @Override
    public Bucket getBucket(String username) {
        User user=userRepository.getUserByPhoneNumber(username);
        if(user==null){
            throw new RuntimeException("User not found");
        }
        Bucket bucket=user.getBucket();
        if(bucket==null){
            throw new RuntimeException("pls add Product to bucket");
        }
        return bucket;
    }

    @Override
    public Product create(ProductCreateDto productCreateDto) {
        return null;
    }


    @Override

    public Product create(ProductCreateDto productCreateDto,List<Long>categoryIds) {
        Product product=new Product();
        product.setName(productCreateDto.getName());
        product.setPrice(productCreateDto.getPrice());
        product.setDescription(productCreateDto.getDescription());
        product.setCategories(getCollectById(categoryIds));
        return save(product);

    }
    private List<Category> getCollectById(List<Long> cotegoryIds){
        return cotegoryIds.stream().map(categoryRepository::getOne).collect(Collectors.toList());
    }

    @Override
    public Product getById(Long id) {
        return productRepository.findById(id).orElseThrow(ProductNotFoundByIdException::new);
    }

    @Override
    public Product update(ProductUpdateDto productUpdateDto) {
        Product product =getById(productUpdateDto.getId());
        if(product==null){
           throw new ProductNotFoundByIdException();
        }
        else {
            if (productUpdateDto.getDescription()!=null)
                product.setDescription(productUpdateDto.getDescription());

            if (productUpdateDto.getPrice()!=null)
                product.setPrice(productUpdateDto.getPrice());

            if(productUpdateDto.getName()!=null)
                product.setName(product.getName());

        }
        return save(product);
    }

    @Override
    public Product save(Product model) {
        return productRepository.save(model);
    }

    @Override
    public boolean existsById(Long id) {
        return true;
    }

    @Override
    public  String createComment(Long productId, CommentCreateDto commentCreateDto) {
        Product product=productRepository.findById(productId).orElseThrow(ProductNotFoundByIdException::new);
        Comment comment=new Comment();
        comment.setDescription(commentCreateDto.getDescription());;
        comment.setRaiting(commentCreateDto.getRaiting());
        if (product.getRaiting()==0){
            product.setRaiting(comment.getRaiting());
        }
        commentRepository.save(comment);
        Double productRaiting=null;
        for (Comment comment1:product.getComments()){
            productRaiting+=comment1.getRaiting();
        }
        comment.setProduct(product);
        product.setRaiting(productRaiting/product.getComments().size());
        return "Comment is create";
    }
}
