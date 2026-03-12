package com.sdust.service.impl;

import com.sdust.mapper.ChatMapper;
import com.sdust.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatServiceImpl implements ChatService {
    @Autowired
    ChatMapper chatMapper;
    @Override
    public int addTitle(Title title) {
        return chatMapper.addTitle(title);
    }

    @Override
    public List<Title> getTitleList(Integer uid) {
        return chatMapper.getTitleList(uid);
    }

    @Override
    public int delTitleById(Integer id) {
        return chatMapper.delTitleById(id);
    }

    @Override
    public int updateTitleById(Title title) {
        return chatMapper.updateTitleById(title);
    }

    @Override
    public int addToTopById(Integer id) {
        return chatMapper.addToTopById(id);
    }

    @Override
    public int addChatRecordMessage(Message message) {
        return chatMapper.addChatRecordMessage(message);
    }

    @Override
    public List<Message> getAllMessageByTid(Integer tid) {
        return chatMapper.getAllMessageByTid(tid);
    }

}
