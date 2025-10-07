package com.furniture.store.service;

import com.furniture.store.dto.request.NotificationTypeRequest;
import com.furniture.store.dto.response.NotificationTypeResponse;
import com.furniture.store.entity.NotificationType;
import com.furniture.store.exception.AppException;
import com.furniture.store.exception.ErrorCode;
import com.furniture.store.mapper.NotificationTypeMapper;
import com.furniture.store.repository.NotificationTypeRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class NotificationTypeService {
    NotificationTypeRepository notificationTypeRepository;
    NotificationTypeMapper notificationTypeMapper;

    public NotificationTypeResponse create(NotificationTypeRequest request) {
        if(notificationTypeRepository.existsByName(request.getName())){
            throw new AppException(ErrorCode.NOTIFICATION_TYPE_ALREADY_EXISTS);
        }

        NotificationType notificationType = notificationTypeMapper.toEntity(request);
        return notificationTypeMapper.toResponse(notificationTypeRepository.save(notificationType));
    }

    public void delete(Long id) {
        NotificationType notificationType = notificationTypeRepository.findById(id)
                .orElseThrow(()->new AppException(ErrorCode.NOTIFICATION_TYPE_NOT_FOUND));

        notificationTypeRepository.delete(notificationType);
    }

    public NotificationTypeResponse update(NotificationTypeRequest request, Long id) {
        NotificationType notificationType = notificationTypeRepository.findById(id)
                .orElseThrow(()->new AppException(ErrorCode.NOTIFICATION_TYPE_NOT_FOUND));

        if(!Objects.equals(notificationType.getName(),request.getName()) &&
                notificationTypeRepository.existsByName(request.getName())) {
            throw new AppException(ErrorCode.NOTIFICATION_TYPE_ALREADY_EXISTS);
        }

        notificationTypeMapper.update(notificationType, request);
        return notificationTypeMapper.toResponse(notificationTypeRepository.save(notificationType));
    }

    public List<NotificationTypeResponse> getAll(){
        return notificationTypeRepository.findAll().stream().map(notificationTypeMapper::toResponse).toList();
    }
}
