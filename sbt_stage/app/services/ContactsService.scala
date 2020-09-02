package services

import java.util.UUID.randomUUID

import javax.inject.Inject
import models.entities.Contact
import models.toDatabase.IContactsRepository

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future


class ContactsService @Inject()(contactsRepository: IContactsRepository[Contact]) extends IContactsService[Contact]
{

  def create(contactForm: ContactData) = Future{

    val contact = Contact(randomUUID().toString, contactForm.name, contactForm.phone)

    contactsRepository.createOrUpdate(contact)

    contact
  }

  def update(id: String, name: String, phone: Long): Future[Contact] = Future{
    val contact = Contact(id, name, phone)

    contactsRepository.createOrUpdate(contact)
    contact
  }

  def delete(id: String): Future[Option[Contact]] = {
    val postFuture = contactsRepository.get(id)
    contactsRepository.delete(id)
    postFuture.map { maybePostData =>
      maybePostData.map { postData =>
        postData
      }
    }
  }

  def lookup(id: String): Future[Option[Contact]] = {
    val postFuture = contactsRepository.get(id)
    postFuture.map { maybePostData =>
      maybePostData.map { postData =>
        postData
      }
    }
  }

  def getAll = {
    contactsRepository.list()
  }

  def findBySubstring(nameSubstring: String, phoneSubstring: String)= {
    contactsRepository.list().map { postDataList =>
      postDataList
        .filter(
          phoneData => phoneData.name.toLowerCase().contains(nameSubstring.toLowerCase())
            && phoneData.number.toString.contains(phoneSubstring.toLowerCase()))
    }
  }
}