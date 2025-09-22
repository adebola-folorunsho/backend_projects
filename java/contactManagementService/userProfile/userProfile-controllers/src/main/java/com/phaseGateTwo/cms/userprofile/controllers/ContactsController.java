package com.phaseGateTwo.cms.userprofile.controllers;

import com.phaseGateTwo.cms.userprofile.dtos.*;
import com.phaseGateTwo.cms.userprofile.services.ContactServices;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contacts")
public class ContactsController {

    private final ContactServices contactService;

    public ContactsController(ContactServices contactService) {
        this.contactService = contactService;
    }

    @GetMapping("/all")
    public ViewUserContactsResponse getAllContacts(Authentication authentication) {
        String userId = authentication.getPrincipal().toString();
        System.out.println(contactService.getAllContactsByUserId(userId).getContactNames());
        return contactService.getAllContactsByUserId(userId);
    }

    @PutMapping("/{contactId}/edit")
    public ResponseEntity<EditContactResponse> editContact(
            @PathVariable String contactId,
            @Valid @RequestBody EditContactRequest request,
            Authentication authentication) {

        String userId = (String) authentication.getPrincipal();
        EditContactResponse response = contactService.editContact(userId, contactId, request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/add")
    public ResponseEntity<AddContactResponse> addContact(
            @Valid @RequestBody AddContactRequest request,
            Authentication authentication) {

        String userId = (String) authentication.getPrincipal();
        AddContactResponse response = contactService.addContact(userId, request);
        return ResponseEntity.ok(response);
    }

    // View a specific contact
    @GetMapping("/{contactId}")
    public ResponseEntity<ViewContactResponse> getContactById(
            @PathVariable String contactId,
            Authentication authentication) {

        String userId = authentication.getPrincipal().toString();
        System.out.println(contactService.getContactById(contactId, userId));
        ViewContactResponse contact = contactService.getContactById(contactId, userId);
        return ResponseEntity.ok(contact);
    }

}
