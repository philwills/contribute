db.getMongo().setSlaveOk();


db.journalists.drop();
db.contributors.drop();
db.requests.drop();
db.responses.drop();


db.createCollection("journalists");
db.createCollection("contributors");
db.createCollection("requests");
db.createCollection("responses");


var contributorBob = new ObjectId();
db.contributors.insert({
	"_id": contributorBob,
	"identity": "bobsmith",
	"expertise": [
		{"what": "Primary care, NHS trusts", "where": "Nottingham", "description": "I have worked in primary care support for over two decades, managing the relationship between contracted service providers and hospital services.", "from": new Date(1985, 1, 1), "to": new Date(2010, 1, 1), "type": "professional"},
		{"what": "Sheds", "description": "I design and build my own sheds and have done for several years.", "from": new Date(1985, 1, 1), "to": new Date(2010, 1, 1), "type": "personal"},
		{"what": "Healthcare Management", "where": "Greenwich University", "from": new Date(1990, 1, 1), "to": new Date(1993, 1, 1), "type": "educational"}
	]
});
var contributorJane = new ObjectId();
db.contributors.insert({
	"_id": contributorJane,
	"identity": "janefellows",
	"expertise": [
		{"what": "Hairdressing", "where": "Nottingham", "description": "I am a freelance hairdresser and have won numerous awards as a colourist.", "from": new Date(2000, 1, 1), "to": new Date(2005, 1, 1), "type": "professional"},
		{"what": "Diabetes", "description": "I've been in and out of hospital all my life due to diabetes. Three years ago I had my leg amputated.", "from": new Date(1980, 1, 1), "type": "personal"},
		{"what": "Hairdressing", "where": "London College of Beauty", "from": new Date(2002, 1, 1), "to": new Date(2004, 1, 1), "type": "educational"}
	]
});
var contributorEmily = new ObjectId();
db.contributors.insert({
	"_id": contributorEmily,
	"identity": "emilygraham",
	"expertise": [
		{"what": "Mental health with adults, NHS management", "where": "Leeds", "description": "I am a clinical physcologist specialising in schizophrenia.", "from": new Date(1981, 1, 1), "type": "professional"},
		{"what": "Singing", "description": "I have been  member of my local church choir for the past 20 years.", "from": new Date(1967, 1, 1), "type": "personal"},
		{"what": "Doctorate in Clinical Psychology", "where": "Kings College London, Institute of Psychiatry", "from": new Date(1989, 1, 1), "to": new Date(1992, 1, 1), "type": "educational"}
	]
});
var contributorSimon = new ObjectId();
db.contributors.insert({
	"_id": contributorSimon,
	"identity": "simonbell",
	"expertise": [
		{"what": "Finance", "where": "London", "description": "I am an independent financial consultant.", "from": new Date(2002, 1, 1), "type": "professional"},
		{"what": "Triathlon", "description": "I joined my local Triathlon club five years ago and have been competing in mid-range events for the past few years.", "from": new Date(2007, 1, 1), "type": "personal"},
		{"what": "Business Management", "where": "University of Warwick", "from": new Date(1996, 1, 1), "to": new Date(1999, 1, 1), "type": "educational"}
	]
});


var journalistDenis = new ObjectId();
db.journalists.insert({
    "_id": journalistDenis,
    "identity": "denis.campbell",
    "groups": [
        {"name": "Reliable on healthcare", "members": [contributorBob, contributorJane, contributorEmily]},
        {"name": "Normally available on weekdays", "members": [contributorJane, contributorSimon]}
    ]
});


var requestHours = new ObjectId();
db.requests.insert({
	"_id": requestHours,
	"title": "Out-of-hours GP services",
	"description": "Have GP services in your area been affected by reforms to the NHS? Patients in Cornwall have complained at changes to out-of-hours services following Serco's takeover. If you are based in Cornwall or elsewhere in the UK and have experienced this in the past 6 months, let us know.",
	"imageUri": "http://static.guim.co.uk/sys-images/Guardian/About/General/2012/5/25/1337943315878/Sercos-headquarters-in-Tr-008.jpg",
	"journalist": journalistDenis,
	"contributors": [contributorBob, contributorJane, contributorEmily, contributorSimon]
});
var requestTenders = new ObjectId();
db.requests.insert({
	"_id": requestTenders,
	"title": "NHS Tenders",
	"description": "We are getting reports that NHS services are being put out to tender as part of the NHS reforms. We are trying to build up a picture of who is bidding for these contracts and what services will be affected. Is this happening where you are?",
	"imageUri": "http://static.guim.co.uk/sys-images/Guardian/Pix/pictures/2012/3/13/1331631661025/Live-discussion-round-up--007.jpg",
	"endDate": new Date(2012, 8, 3),
	"journalist": journalistDenis,
	"contributors": [contributorBob, contributorJane]
});
var requestAbuse = new ObjectId();
db.requests.insert({
	"_id": requestAbuse,
	"title": "Abuse in the NHS and social care",
	"description": "Witnessed a risk, malpractice or wrongdoing in the NHS or in social care which adversely affects patients, the public or the organisation itself? Our social affairs team wants to hear from you.",
	"imageUri": "http://static.guim.co.uk/sys-images/Guardian/Pix/pictures/2010/10/14/1287081517905/London-NHS-006.jpg",
	"journalist": journalistDenis,
	"contributors": [contributorEmily, contributorSimon]
});
var requestMail = new ObjectId();
db.requests.insert({
	"_id": requestMail,
	"title": "Have you been made redundant or retired from the Royal Mail?",
	"description": "The Royal Mail has lost 4,000 employees through retirement or redundancy since March 2011. Have you or a friend or relative been affected by these cutbacks? I'm looking for background information on what's been happening with the Royal Mail as it moves towards privatisation.",
	"imageUri": "http://static.guim.co.uk/sys-images/Guardian/About/General/2009/4/6/1239022426359/A-Royal-Mail-postman-deli-001.jpg",
	"endDate": new Date(2012, 8, 31),
	"journalist": journalistDenis,
	"contributors": [contributorJane, contributorEmily, contributorSimon]
});


db.responses.insert({
	"contributor": contributorBob,
	"request": requestHours,
	"text": "I have been affected by this blah blah",
	"imageUri": "http://www.lifewithcats.tv/wp-content/uploads/2011/02/funny-cats-a10.jpg",
	"date": new Date(2012, 7, 1)
});
db.responses.insert({
	"contributor": contributorJane,
	"request": requestTenders,
	"text": "I can't believe it",
	"imageUri": "http://1.bp.blogspot.com/-DWuxqSoiSIE/T1lveIZLnXI/AAAAAAAACRg/MlyINr8UOAc/s1600/Very-funny-cat.jpg",
	"date": new Date(2012, 7, 2)
});
db.responses.insert({
	"contributor": contributorEmily,
	"request": requestAbuse,
	"text": "I know something",
	"imageUri": "http://www.freyacat.co.uk/wp-content/uploads/2009/06/Ceiling-Cat-Picture.jpg",
	"date": new Date(2012, 7, 3),
	"endorsed": true
});
db.responses.insert({
	"contributor": contributorSimon,
	"request": requestMail,
	"text": "I am useful somehow",
	"date": new Date(2012, 7, 4)
});
db.responses.insert({
	"contributor": contributorEmily,
	"request": requestMail,
	"text": "Hello",
	"date": new Date(2012, 7, 5)
});


