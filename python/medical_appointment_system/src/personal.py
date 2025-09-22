import re
from abc import ABC, abstractmethod
from datetime import datetime
from typing import Optional

from utilities.contact import Contact

class Personal(ABC):
	def __init__(self, first_name: str, last_name: str, date_of_birth: str):
		self.__validate_name(first_name)
		self.__first_name = first_name.title()

		self.__validate_name(last_name)
		self.__last_name = last_name.title()

		self.__date_of_birth = self.__validate_dob(date_of_birth)
		self.__contact_information = Contact()
		self.__id = None

	@property
	def first_name(self) -> str: return self.__first_name

	@first_name.setter
	def first_name(self, first_name_input: str):
		self.__validate_name(first_name_input)
		self.__first_name = first_name_input.title()

	@property
	def last_name(self) -> str: return self.__last_name

	@last_name.setter
	def last_name(self, last_name_input: str):
		self.__validate_name(last_name_input)
		self.__last_name = last_name_input.title()

	@staticmethod
	def __validate_name(name: str):
		if re.fullmatch(r"^([a-z]+)([-']?)([a-z]+)$", name, re.I) is None: raise ValueError("Invalid name")
		"""maybe allow space/underscore character for middle names"""

	@property
	def id(self):
		return self.__id

	@id.setter
	def id(self, assigned_input: str):
		self.__id = assigned_input

	@property
	def full_name(self):
		full_name_is = self.first_name + " " + self.last_name
		return full_name_is.title().strip()

	@property
	def date_of_birth(self) -> str: return self.__date_of_birth

	@date_of_birth.setter
	def date_of_birth(self, date_of_birth_input: str):
		self.__date_of_birth = self.__validate_dob(date_of_birth_input)

	@staticmethod
	def __validate_dob(date_of_birth: str) -> Optional[str]:
		try:
			dob = datetime.strptime(date_of_birth, "%d/%m/%Y")
		except ValueError:
			raise ValueError("Invalid, date of birth should be in format DD/MM/YYYY. eg. 31/01/2025")
		if dob > datetime.now(): raise ValueError("Invalid date of birth")
		return dob.strftime("%d/%m/%Y")
