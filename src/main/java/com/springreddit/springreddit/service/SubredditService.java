package com.springreddit.springreddit.service;

import com.springreddit.springreddit.dto.SubredditDto;
import com.springreddit.springreddit.exceptions.SpringRedditException;
import com.springreddit.springreddit.mapper.SubredditMapper;
import com.springreddit.springreddit.model.Subreddit;
import com.springreddit.springreddit.repository.SubredditRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class SubredditService {

    private final SubredditRepository subredditRepository;
    private final SubredditMapper subredditMapper;
    private final AuthService authService;

    @Transactional
    public SubredditDto save(SubredditDto subredditDto) {
        Subreddit s = subredditMapper.mapDtoToSubreddit(subredditDto);
        //s.setCreatedDate(Instant.now());
        s.setUser(authService.getCurrentUser());
        Subreddit save = subredditRepository.save(s);
        subredditDto.setId(save.getId());
        return subredditDto;
    }

    @Transactional(readOnly = true)
    public List<SubredditDto> getAll() {
        return subredditRepository.findAll()
                .stream()
                .map(subredditMapper::mapSubredditToDto)
                .collect(Collectors.toList());
    }

    public SubredditDto getSubreddit(Long id) {
        Subreddit subreddit = subredditRepository.findById(id)
                .orElseThrow(() -> new SpringRedditException("No subreddit found with ID - " + id));
        return subredditMapper.mapSubredditToDto(subreddit);
    }
}